async function carregarAdotantes(filtroNome = "", tipoFiltro = "todos") {
    try {
        const [resAdotantes, resDevolvidos] = await Promise.all([
            fetch('/api/adotantes'),
            fetch('/api/adocoes/detalhesDevolvidos')
        ]);

        const dadosAdotantes = await resAdotantes.json();
        const dadosDevolvidos = await resDevolvidos.json();
        const idsBloqueados = dadosDevolvidos.map(item => item.adotanteId);

        const corpoTabela = document.getElementById('tabela-adotantes');
        let html = '';

        const filtrados = dadosAdotantes.filter(adt => {
            const temDevolucao = idsBloqueados.includes(adt.id);
            const bateNome = adt.nome.toLowerCase().includes(filtroNome.toLowerCase());

            if (tipoFiltro === "regulares") return bateNome && !temDevolucao;
            if (tipoFiltro === "bloqueados") return bateNome && temDevolucao;
            return bateNome; // "todos"
        });

        filtrados.forEach(adt => {
            const temDevolucao = idsBloqueados.includes(adt.id);
            const statusHTML = temDevolucao ? 
                '<b style="color: #dc3545;">❌ BLOQUEADO</b>' : 
                '<b style="color: #28a745;">✅ Regular</b>';

            html += `
                <tr>
                    <td>${adt.id}</td>
                    <td>${adt.nome}</td>
                    <td>${adt.telefone}</td>
                    <td>${adt.email}</td>
                    <td>${statusHTML}</td>
                    <td>
                        <button onclick="prepararAtualizacao(${adt.id})" ${temDevolucao ? 'disabled' : ''}>Editar</button>
                        <button onclick="excluirAdotante(${adt.id})" ${temDevolucao ? 'disabled' : ''}>Excluir</button>
                        
                    </td>
                </tr>`;
        });
        corpoTabela.innerHTML = html;
    } catch (e) { console.error(e); }
}

document.addEventListener('DOMContentLoaded', function() {
    
    const formCadastro = document.getElementById('form-cadastro');
    if (formCadastro) {
        formCadastro.addEventListener('submit', function(event) {
            event.preventDefault(); 

            const novoAdotante = {
                nome: document.getElementById('nome').value,
                telefone: document.getElementById('telefone').value,
                email: document.getElementById('email').value
            };

            fetch('/api/adotantes', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(novoAdotante)
            })
            .then(res => {
                if (res.ok) {
                    alert("Adotante cadastrado com sucesso!");
                    window.location.href = "adotantes.html"; 
                }else{
					alert("Adotante já existe no sistema");
				}
            });
        });
    }

	const tabela = document.getElementById('tabela-adotantes');
    
    if (tabela) {
        const campoBusca = document.getElementById('campoBusca');
        const btnTodos = document.getElementById('btnTodos');
        const btnRegular = document.getElementById('btnRegular');
        const btnBloqueado = document.getElementById('btnBloqueado');

        if (btnTodos) {
            btnTodos.addEventListener('click', () => {
                carregarAdotantes(campoBusca.value, "todos");
            });
        }

        if (btnRegular) {
            btnRegular.addEventListener('click', () => {
                carregarAdotantes(campoBusca.value, "regulares");
            });
        }

        if (btnBloqueado) {
            btnBloqueado.addEventListener('click', () => {
                carregarAdotantes(campoBusca.value, "bloqueados");
            });
        }

        carregarAdotantes("", "todos");
    }

    
});

function excluirAdotante(id) {
    if (confirm("Deseja realmente excluir este registro?")) {
        fetch(`/api/adotantes/${id}`, { method: 'DELETE' })
        .then(async response => {
            if (response.ok) {
                alert("Adotante excluído com sucesso!");
                carregarAdotantes(); // Recarrega sua lista
            } else {
                const mensagemErro = await response.text();
                alert("Não foi possível excluir: " + mensagemErro);
            }
        })
        .catch(error => {
            console.error("Erro na rede:", error);
            alert("Erro de conexão ao tentar excluir.");
        });
    }
}


function prepararAtualizacao(id) {
    fetch(`/api/adotantes/${id}`)
        .then(res => res.json())
        .then(adt => {
            const n = prompt("Nome:", adt.nome);
            const t = prompt("Telefone:", adt.telefone);
            const e = prompt("Raça:", adt.email);
            
            fetch(`/api/adotantes/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({nome: n, telefone: t, email: e})
            }).then(() => carregarAdotantes());
        });
}