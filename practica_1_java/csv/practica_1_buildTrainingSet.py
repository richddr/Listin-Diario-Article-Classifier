__author__ = 'Richard Garcia || Ricardo Batista'

import feedparser
import nltk
#"http://www.listindiario.com/rss/portada/",
feeds = ["http://www.listindiario.com/rss/elnorte/",
         "http://www.listindiario.com/rss/economia/",
         "http://www.listindiario.com/rss/editorial/",
         "http://www.listindiario.com/rss/opinion/",
         "http://www.listindiario.com/rss/ventana/",
         "http://www.listindiario.com/rss/larepublica/",
         "http://www.listindiario.com/rss/lasmundiales/",
         "http://www.listindiario.com/rss/lavida/",
         "http://www.listindiario.com/rss/eldeporte/",
         "http://www.listindiario.com/rss/entretenimiento/",
         "http://www.listindiario.com/rss/sociales/"]

file = open('listin-articulos.csv', 'a')
#file.write("id,articulo,clase\r\n")
id_articulo = 0
for i in feeds:
    categoria = i.split('/')
    print(categoria[4])
    file_cat = open('listin-articulos-td-'+categoria[4]+'.csv', 'a')
    #file_cat.write("id,articulo,clase\r\n")
    feed = feedparser.parse(i)
    for i in range(0, len(feed.entries)):#accesamos cada articulo del rss
        if(len(feed.entries[i]['summary']) > 0):
            articulo = feed.entries[i]['title']+" "+feed.entries[i]['summary']
            articulo = str(articulo.replace(',', " "))
            id_articulo = id_articulo+1
            if categoria[4] == "eldeporte":
                file.write(str(id_articulo)+","+articulo+",si\r\n")
                file_cat.write(str(id_articulo)+","+articulo+",si\r\n")
            else:
                file.write(str(id_articulo)+","+articulo+",no\r\n")
                file_cat.write(str(id_articulo)+","+articulo+",no\r\n")
    file_cat.close()
file.close()


