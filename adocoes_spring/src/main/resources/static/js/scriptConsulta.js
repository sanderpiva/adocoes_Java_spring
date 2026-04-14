async function carregarConsultaDetalhada(termoBusca = "") {
    try {
        console.log("Buscando relatório detalhado...");
        
        const resposta = await fetch('/api/adocoes/detalhes');
        const dados = await resposta.json();

        const corpoTabela = document.getElementById('tabela-relatorio');
        if (!corpoTabela) return;

        corpoTabela.innerHTML = ''; 

        const dadosFiltrados = dados.filter(adocao => {
            const nomeAnimal = adocao.nomeAnimal ? adocao.nomeAnimal.toLowerCase() : "";
            return nomeAnimal.includes(termoBusca.toLowerCase());
        });

        let html = '';
        dadosFiltrados.forEach(adocao => {
            const corStatus = adocao.status === 'DEVOLVIDA' ? '#dc3545' : '#28a745';

            html += `
                <tr>
                    <td>${adocao.adocaoId}</td>
                    <td>${adocao.nomeAdotante}</td>
                    <td>${adocao.telefone}</td>
                    <td>${adocao.email}</td>
                    <td>${adocao.nomeAnimal}</td>
                    <td>${adocao.data}</td>
                    <td style="color: ${corStatus}; font-weight: bold;">${adocao.status}</td>
                </tr>`;
        });

        corpoTabela.innerHTML = html;

        if (dadosFiltrados.length === 0 && termoBusca !== "") {
            corpoTabela.innerHTML = `<tr><td colspan="7" style="text-align:center;">Nenhuma adoção encontrada para o animal: "${termoBusca}"</td></tr>`;
        }

    } catch (erro) {
        console.error('Erro ao buscar relatório:', erro);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    
    carregarConsultaDetalhada();

    const btnBuscar = document.getElementById('btnBuscar');
    const campoBusca = document.getElementById('buscaID');

    if (btnBuscar && campoBusca) {
        btnBuscar.addEventListener('click', function() {
            const termo = campoBusca.value;
            carregarConsultaDetalhada(termo);
        });

        campoBusca.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                carregarConsultaDetalhada(campoBusca.value);
            }
        });
    }
});