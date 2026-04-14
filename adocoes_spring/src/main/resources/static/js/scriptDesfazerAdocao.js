document.getElementById('formDesfazerAdocao').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede a página de recarregar

    const id = document.getElementById('inputAdocaoId').value;

    if (confirm(`Deseja realmente cancelar a adoção ID ${id}?`)) {
        
        fetch(`/api/adocoes/${id}`, {
            method: 'DELETE'
        })
        .then(async response => {
            const mensagem = await response.text();
            
            if (response.ok) {
                alert(mensagem); 
                window.location.href = 'index.html'; 
            } else {
                
                alert("Erro: " + mensagem);
            }
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
            alert("Erro de conexão com o servidor.");
        });
    }
});