import validaDado from "./valida.js"

var nome = document.getElementById('nomeCompleto')
var cpf = document.getElementById('CPF')
var endereco = document.getElementById('endereco')
var numero = document.getElementById('numero')
var numCelular = document.getElementById('telCelular')


var botao = document.getElementById('botaoSubmit')

botao.addEventListener('click',
    ()=>{
                var termo = document.getElementById('terms')
                if(termo.checked){
                    validaDado(nome,cpf,endereco,numero,numCelular)
                }else{
                    alert(`Por favor consinta com o termo para seguir com cadastro.`)
                }
        }
    )