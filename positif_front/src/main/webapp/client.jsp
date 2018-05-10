<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscription</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.css">
        <script
          src="https://code.jquery.com/jquery-3.3.1.min.js"
          integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
          crossorigin="anonymous">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>
    </head>
    <body style="background: linear-gradient(to right, #fafafa, #fefefe)">
        <div class="ui padded centered grid">
            <div class="fifteen wide column">
                <div class="ui top attached tabular menu">
                  <a class="active item" data-tab="first">Profil Astrologique</a>
                  <a class="item" data-tab="second">Consulter un médium</a>
                  <a class="item" data-tab="third">Historique des consultations</a>
                  <button onclick="window.location='index.html'" class="big ui icon item button">
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
            <script type="text/javascript">
                $('.menu .item')
                  .tab()
                ;
            </script>  
    </body>
</html>
