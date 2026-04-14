let bancoDeDadosLocal = []; 

function carregarConsultaDevolvidos() {
    console.log("Conectando ao servidor...");
    fetch('/api/adocoes/detalhesDevolvidos')
        .then(res => res.json())
        .then(dados => {
            bancoDeDadosLocal = dados; 
            renderizarMinhaTabela(bancoDeDadosLocal); 
        })
        .catch(err => {
            console.error("Erro fatal:", err);
            alert("Não foi possível carregar os dados. O servidor Java está ligado?");
        });
}


function renderizarMinhaTabela(lista) {
    const tabela = document.getElementById('tabela-relatorio');
    if (!tabela) return;

    tabela.innerHTML = ""; 

    if (lista.length === 0) {
        tabela.innerHTML = "<tr><td colspan='7'>Nenhum registro encontrado.</td></tr>";
        return;
    }
    
    
    lista.forEach(item => {
		const corStatus = item.status === 'DEVOLVIDA' ? '#dc3545' : '#28a745';
        const linha = `<tr>
            <td>${item.adocaoId}</td>
            <td>${item.adotanteId}</td>
            <td>${item.nomeAdotante}</td>
            <td>${item.telefone}</td>
            <td>${item.email}</td>
            <td>${item.nomeAnimal}</td>
            <td>${item.data}</td>
            <td style="color: ${corStatus}; font-weight: bold;">${item.status}</td>
        </tr>`;
        tabela.innerHTML += linha;
    });
}


document.addEventListener('DOMContentLoaded', () => {
    carregarConsultaDevolvidos(); 

    const botao = document.getElementById('btnBuscar');
    const input = document.getElementById('buscaIDdevolvido');

    if (botao) {
        botao.onclick = function() {
            const termo = input.value.toLowerCase().trim();
            console.log("Buscando por: " + termo);

            const resultados = bancoDeDadosLocal.filter(adocao => {
                const nomeAnimal = adocao.nomeAnimal ? adocao.nomeAnimal.toLowerCase() : "";
                return nomeAnimal.includes(termo);
            });

            renderizarMinhaTabela(resultados);
        };
    }
    

});