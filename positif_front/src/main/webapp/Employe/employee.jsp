<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Employé</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.css">
        <link rel="stylesheet" type ="text/css" href="../my_styles.css" />
        <script
          src="https://code.jquery.com/jquery-3.3.1.min.js"
          integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
          crossorigin="anonymous">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>
        <style>
          .equal.width .column {
            font-style: italic;
          }
          .equal.width .column.right.aligned {
            font-style: normal;
          }
        </style>  
    </head>
    <body style="background: linear-gradient(to right, #fafafa, #fefefe)">
        <div class="ui padded centered grid">
            <div class="fifteen wide column">
                <div class="ui top attached tabular menu">
                  <a class="active item" data-tab="first">Tableau de Bord</a>
                  <button class="ui item button" onclick="$('#voyance_modal').modal('show');">Voyance</a>
                  <button id="deconnexion" class="big ui icon item button" data-content="Se déconnecter" data-position="right center" data-variation="mini">
                      <i class="red power icon"></i> 
                  </button>
                </div>
                <div class="ui bottom attached active tab segment" data-tab="first">
                </div>
            </div>  
                <div class="my_alert">
                    <div id="server_error" class="mini error ui message">
                        <i class="close icon" onclick=" $(this).closest('.message').transition('zoom');"></i>
                        <div class="header">
                            Erreur interne du serveur
                        </div>
                        <p>Veuillez réessayer ultérieurement.</p>
                    </div>
                    <div id="deco_error" class="mini error ui message">
                        <i class="close icon" onclick=" $(this).closest('.error.message').transition('zoom');"></i>
                        <div class="header">
                            Échec de la déconnexion
                        </div>
                    </div>
                    <div id="no_client_error" class="mini error ui message">
                        <i class="close icon" onclick=" $(this).closest('.error.message').transition('zoom');"></i>
                        <div class="header">
                            Ce client n'a demandé aucune consultation
                        </div>
                    </div>
                </div> 
                <div id="voyance_modal" class="ui modal">
                    <i class="close icon"></i>
                    <div class="header">
                        Effectuer une voyance
                    </div>
                    <div class="content">
                        <form id="no_client_form" class="ui form" method="POST" action="../ActionServlet">
                            <div class="field">
                                <label for="no_client">N° Client</label>
                                <input type="text" id="no_client" name="no_client">
                            </div>
                            <input type="hidden" name="todo" value="obtenirVoyanceDemandee" />
                        </form>
                    </div>
                    <div class="actions">
                        <div class="ui red deny button">
                            Retour
                        </div>
                        <div class="ui positive right labeled icon button" onclick="$('#no_client_form').submit();">
                            Confirmer
                            <i class="checkmark icon"></i>
                        </div>
                    </div>
                </div>   
            </div>  
            <script type="text/javascript">
                $('.menu .item')
                  .tab()
                ;
                $('#deconnexion').popup();
                
                $('document').ready(() => {

                console.log('document is ready');
                $('#deconnexion').on('click', function() {
                    $.ajax({
                        url: '../ActionServlet',
                        type: 'GET',
                        data: 'todo=deconnexion',
                        success: () => {
                            window.location = 'index.html';
                        },
                        error: function(xhr, resp, text) {
                            $('#deco_error').transition('jiggle');
                        }
                    });
                });

                $('#no_client_form').on('submit', function(event) {
                    event.preventDefault();
                    console.log('no_client_form submitted');   
                    console.log($(this).serialize());  
                    $('.my_alert').children().hide();              
                    $.ajax({
                        url: $(this).attr('action'),
                        type: $(this).attr('method'),
                        dataType: 'json',
                        data: $(this).serialize(),
                        success: (resp) => {
                            if(resp.success) {
                                window.location = 'voyance.html';  
                            }
                            else {
                                $('#no_client_error').transition('jiggle');
                            }
                        },
                        error: function(xhr, resp, text) {
                            $('#server_error').transition('jiggle');
                        }
                    });
                });

            });
            </script>  
    </body>
</html>
