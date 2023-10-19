window.addEventListener('load', function () {


document.getElementById("btnBuscarOdontologo").addEventListener("click" , () =>{

     var id = document.getElementById("inputValue").value;


     const url = '/odontologo/'+id;
              const settings = {
                method: 'GET'
              }

             fetch(url,settings)
                   .then(response => response.json())
                   .then(data => {
                   //recorremos la colección de peliculas del JSON
                      console.log(data)
                         //por cada pelicula armaremos una fila de la tabla
                         //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
                         var card = document.getElementById("odontologoForId");


                         //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                         //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                         //de llamar a la API para eliminar una pelicula
                         let deleteButton = '<button' +
                                                   ' id=' + '\"' + 'btn_delete_' + data.id + '\"' +
                                                   ' type="button" onclick="deleteBy('+data.id+')" class="btn btn-danger btn_delete">' +
                                                   'eliminar' +
                                                   '</button>';

                         //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
                         //a la función de java script findBy que se encargará de buscar la pelicula que queremos
                         //modificar y mostrar los datos de la misma en un formulario.
                         let updateButton = '<button ' +
                                                   ' id=' + '\"' + 'btn_id_' + data.id + '\"' +
                                                   ' type="button" onclick="findBy('+data.id+')" class="btn btn-info btn_id mt-3"> Actualizar </button>';

                         //armamos cada columna de la fila
                         //como primer columna pondremos el boton modificar
                         //luego los datos de la pelicula
                         //como ultima columna el boton eliminar
                         card.innerHTML =
                                '<div class="card mt-3" style="width: 18rem;">'+
                                '<div class="card-body">'+
                                '<h5 class="card-title">'+ data.nombre +' '+ data.apellido +'</h5>'  +
                                '<p class="card-text">Matricula : '+data.matricula+'</p>'+
                                '<div> '+updateButton+'</div>'+
                                '<div> '+deleteButton+'</div>';
;


    })

})


    })