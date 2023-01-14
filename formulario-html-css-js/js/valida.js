function validaDado(a ,b ,c ,d ,e ){
    if(a.value == ""){
        alert(`Campo não informado!`)
        a.focus()
        return;
    }
    if(b.value == ""){
        alert(`campo não informado!`)
        b.focus()
        return;
    }
    if(c.value == ""){
        alert(`campo não informado!`)
        c.focus()   
        return;
    }
    if(d.value == ""){
        alert(`campo não informado!`)
        d.focus()
        return;
    }
    if(e.value == ""){
        alert(`campo não informado!`)
        e.focus()
        return;
    }else{
        alert(`Cadastro realizado com sucesso !`)
    }
}

export default validaDado