var apikey= {
     key: 289cdf61-f05b-4f93-a058-cd64fb32d66f
    };

fetch('https://pro-api.coinmarketcap.com/v1/cryptocurrency/map?CMC_PRO_API_KEY='+
    apikey.key).then((response) => {
        if(!response.ok)throw new Error('erro ao executar a requisição'+ response.status);
        return response.json();
    })
    .then((api) => {
        console.log(api);
    })
    .catch((error)=> {
        console.log(error.message);
    });