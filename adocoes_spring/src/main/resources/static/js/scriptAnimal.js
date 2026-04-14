let bancoDeDadosLocal = []; 

function carregarAnimais() {
    console.log("Tentando carregar animais...");

    fetch('/api/animais')
        .then(res => res.json())
        .then(dados => {
            bancoDeDadosLocal = dados; 
            console.log("Dados recebidos:", bancoDeDadosLocal);
            
            renderizarMinhaTabela(bancoDeDadosLocal);
        })
        .catch(err => console.error("Erro ao carregar lista:", err));
}

function renderizarMinhaTabela(dados) {
    const corpoTabela = document.getElementById('tabela-animais');
    if (!corpoTabela) return;

    let html = '';
    dados.forEach(a => {
        
        const statusDisponivel = a.disponivel === 1 ? "Sim" : "Não";

        html += `
            <tr>
                <td>${a.id}</td>
                <td>${a.nome}</td>
                <td>${a.especie}</td>
                <td>${a.raca}</td>
                <td>${a.descricao}</td>
                <td>${statusDisponivel}</td>
                <td>
                    <button onclick="prepararAtualizacao(${a.id})" style="background-color: #ffc107;">Editar</button>
                    <button onclick="excluirAnimal(${a.id})" style="background-color: #dc3545; color: white;">Excluir</button>
                </td>
            </tr>`;
    });
    corpoTabela.innerHTML = html;
}

document.addEventListener('DOMContentLoaded', function() {
    
    if (document.getElementById('tabela-animais')) {
        carregarAnimais();
    }

    const btnBuscar = document.getElementById('btnBuscar');
    const btnLimpar = document.getElementById('btnLimpar');
    const input = document.getElementById('buscaIDdisponivel');

    if (btnBuscar) {
        btnBuscar.onclick = function() {
            const termo = input.value.toLowerCase().trim();
            console.log("Buscando por: " + termo);

            const resultados = bancoDeDadosLocal.filter(animal => {
                const nomeAnimal = animal.nome ? animal.nome.toLowerCase() : "";
                
                return nomeAnimal.includes(termo) && animal.disponivel === 1;
            });

            renderizarMinhaTabela(resultados);
        };
    }
    
    if(btnLimpar){
		btnLimpar.onclick = function() {

            carregarAnimais();
        };
	
	}

    const formCadastro = document.getElementById('form-cadastro');

    if (formCadastro) {
        formCadastro.addEventListener('submit', function(event) {
            event.preventDefault(); 

            const novoAnimal = {
                nome: document.getElementById('nome').value,
                especie: document.getElementById('especie').value,
                raca: document.getElementById('raca').value,
                descricao: document.getElementById('descricao').value,
                disponivel: parseInt(document.getElementById('disponivel').value)
            };

            fetch('/api/animais', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(novoAnimal)
            })
            .then(res => {
                if (res.ok) {
                    alert("Animal cadastrado com sucesso!");
                    window.location.href = "animais.html"; 
                }
            });
        });
    }
});

function excluirAnimal(id) {
    if (confirm("Deseja realmente excluir este registro?")) {
        fetch(`/api/animais/${id}`, { method: 'DELETE' })
        .then(async response => {
            if (response.ok) {
                alert("Animal excluído com sucesso!");
                carregarAnimais(); 
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
    fetch(`/api/animais/${id}`)
        .then(res => res.json())
        .then(a => {
            const n = prompt("Nome:", a.nome);
            const e = prompt("Espécie:", a.especie);
            const r = prompt("Raça:", a.raca);
            const d = prompt("Descrição:", a.descricao);
            const disp = prompt("Disponível? (1-Sim, 0-Não):", a.disponivel);

            fetch(`/api/animais/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({nome: n, especie: e, raca: r, descricao: d, disponivel: parseInt(disp)})
            }).then(() => carregarAnimais());
        });
}