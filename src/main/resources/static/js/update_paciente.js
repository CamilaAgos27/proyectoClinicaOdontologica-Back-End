window.addEventListener('load', function () {


    const formulario = document.querySelector('#edit_paciente');

    formulario.addEventListener('submit', function (event) {
    event.preventDefault()
        let pacienteId = document.querySelector('#paciente_id').value;


        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaAlta').value,
            email: document.querySelector('#email').value,
            domicilio:{
                            calle: document.querySelector('#calle').value,
                            numero: document.querySelector('#numero').value,
                            localidad: document.querySelector('#localidad').value,
                            provincia: document.querySelector('#provincia').value,
                            }

        };

        const url = '/paciente';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => window.location.reload())

    })
 })

function findBy(id) {
          const url = '/paciente'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              console.log(paciente)
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre').value=paciente.nombre;
              document.querySelector('#apellido').value=paciente.apellido;
              document.querySelector('#dni').value=paciente.dni;
              document.querySelector('#fechaAlta').value=paciente.fechaAlta;
              document.querySelector('#email').value=paciente.email;
              document.querySelector('#calle').value=paciente.domicilio.calle;
              document.querySelector('#numero').value=paciente.domicilio.numero;
              document.querySelector('#localidad').value=paciente.domicilio.localidad;
              document.querySelector('#provincia').value=paciente.domicilio.provincia;
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              console.log(error)
          })
      }

  function deleteBy(id)
  {

            const url = '/paciente/'+id;
            const settings = {
                method: 'DELETE'
            }
            fetch(url,settings)
            .then(response => response.json())
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();

  }