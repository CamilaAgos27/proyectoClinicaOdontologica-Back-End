window.addEventListener('load', function () {


    const formulario = document.querySelector('#edit_turno');

    formulario.addEventListener('submit', function (event) {
    event.preventDefault()


        const formData = {
            id: document.querySelector('#turno_id').value,
            fecha: document.querySelector('#fecha').value,
            pacienteId: document.querySelector('#paciente_id').value,
            odontologoId: document.querySelector('#odontologo_id').value
        };


        const url = '/turno';
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
          const url = '/turno'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#fecha').value=turno.fecha;
              document.querySelector('#div_turno_updating').style.display = "block";
              document.querySelector('#paciente_id').value = turno.pacienteId
              document.querySelector('#odontologo_id').value = turno.odontologoId
          }).catch(error => {
              console.log(error)
          })
      }

  function deleteBy(id)
  {

            const url = '/turno'+"/"+id;
            const settings = {
                method: 'DELETE'
            }
            fetch(url,settings)
            .then(response => response.json())


  }