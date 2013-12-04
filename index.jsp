<!--
 *
 *  Copyright (c) Brent Parish and David McMurray
 *
 *-->
<!DOCTYPE html>
<jsp:useBean id="engine" class="b_src.Engine" scope="session" /> 
<html>
    <head>
        <meta name="description" content="Search and discover beautiful social content in one much easier feed.">
        <meta name="keywords" content="Twitter, Instagram, Posts, Social, Media, Feed">
        <meta name="author" content="Brent Parish &amp; Dave McMurray">
        <meta name="application-name" content="Muchly">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Muchly</title>

        <!-- Add JQuery -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="b_script/script.js"></script>
        <script src="b_script/ga.js"></script>
        <link rel="stylesheet" type="text/css" href="b_css/main.css" />
        <link href='http://fonts.googleapis.com/css?family=Lato:300' rel='stylesheet' type='text/css'>
 <style>
        </style>
    </head>
    <body>
            <!-- Get Search parameter from URI -->
           <%
        //if ((request.getParameter("searchVal") != "") && (request.getParameter("searchVal") != null))
        //{
                        engine.set_searchVal(request.getParameter("searchVal"),request.getParameter("top"));
          //          }
            %>

            <header id="top" class="row header-1" role="banner">
            <div class="pad-normal pad-right-medium">
                <nav id="nav" role="navigation">
                    <form method="get" action="index.jsp">  
                        <input placeholder="<%=engine.get_searchVal()%>" class="input-1" type="text" name="searchVal" autofocus="autofocus" /> <!---todo: put in last val searched for-->
                        <input type="submit" class="button-1" value="" />        
                    </form>
                </nav>
            </div>
        </header>                
        <div id="loader" class="wrapperloading">
          <div class="loading up"></div>
        </div>
        <div class="masonry js-masonry" id="container" role="main">
            <article class="item">
                <%= engine.next()%>
            </article>         
            <article class="item ">
                <%= engine.next()%>
            </article> 
            <article class="item ">
                <%= engine.next()%>
            </article>        
            <article class=" item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article>         
            <article class="item ">
                <%= engine.next()%>
            </article> 
            <article class="item ">
                <%= engine.next()%>
            </article>        
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article>         
            <article class="item ">
                <%= engine.next()%>
            </article> 
            <article class="item ">
                <%= engine.next()%>
            </article>        
            <article class=" item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article>         
            <article class="item ">
                <%= engine.next()%>
            </article> 
            <article class="item ">
                <%= engine.next()%>
            </article>        
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
            <article class="item">
                <%= engine.next()%>
            </article> 
        </div><div id="loadmoreajaxloader" style="display:none;"><center><img src="http://www.jquery4u.com/demos/infinite-scrolling-demo2/ajax-loader.gif" /></center></div>
        <script type="text/javascript" src="b_script/imagesloaded.pkgd.min.js"></script>
        <script type="text/javascript" src="b_script/masonry.pkgd.min.js"></script>
        </body>
</html>
