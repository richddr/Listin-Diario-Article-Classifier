import feedparser
__author__ = 'Richard'

file = open('/csv/listin-articulos-test.csv', 'w')
file.write("id,articulo,clase\r\n")
id_articulo = 0
url = "http://www.listindiario.com/rss/portada/"
feed = feedparser.parse(url)
categoria = url.split('/')
print(categoria[4])
for i in range(0, len(feed.entries)):#accesamos cada articulo del rss
    if(len(feed.entries[i]['summary']) > 0):
        articulo = feed.entries[i]['title']+" "+feed.entries[i]['summary']
        articulo = str(articulo.replace(',', " "))
        id_articulo = id_articulo+1
        if categoria[4] == "eldeporte":
            file.write(str(id_articulo)+","+articulo+",si\r\n")
        else:
            file.write(str(id_articulo)+","+articulo+",no\r\n")
file.close()