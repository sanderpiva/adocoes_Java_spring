document.getElementById('formAdocao').addEventListener('submit', function(e) {
    e.preventDefault();

    const dados = {
        adotante: { id: parseInt(document.getElementById('adotanteId').value) },
        animal: { id: parseInt(document.getElementById('animalId').value) },
        data: document.getElementById('dataAdocao').value
    };

    fetch('/api/adocoes/salvar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dados)
    })
    .then(res => {
        if (res.ok) {
            alert('Adoção realizada com sucesso!');
            
            window.location.href = 'consulta-geral.html'; 
        } else {
            res.text().then(mensagemDeErro => {
                alert('Erro: ' + mensagemDeErro);
            });
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Erro de conexão com o servidor.');
    });
});