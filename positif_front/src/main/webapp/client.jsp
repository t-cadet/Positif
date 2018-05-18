<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscription</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.css">
        <link rel="stylesheet" type ="text/css" href="my_styles.css" />
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
                  <a class="active item" data-tab="first">Profil Astrologique</a>
                  <a class="item" data-tab="second">Consulter un Médium</a>
                  <a class="item" data-tab="third">Historique des Consultations</a>
                  <button id="deconnexion" class="big ui icon item button" data-content="Se déconnecter" data-position="right center" data-variation="mini">
                      <i class="red power icon"></i> <!--TODO implémenter la vraie fonction de déconnexion -->
                  </button>
                </div>
                <div class="ui bottom attached active tab segment" data-tab="first">
                    <%@ include file="profile.html" %>
                </div>
                <div class="ui bottom attached tab segment" data-tab="second">
                    <%@ include file="consult.html" %>
                </div>
                <div class="ui bottom attached tab segment" data-tab="third">
                    <%@ include file="history.html" %>
                </div>
            </div>  
                <div class="my_alert">
                    <div id="deco_error" class="mini error ui message">
                        <i class="close icon" onclick=" $(this).closest('.error.message').transition('zoom');"></i>
                        <div class="header">
                            Échec de la déconnexion
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
                        url: 'ActionServlet',
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
            });
            </script>  
    </body>
</html>
