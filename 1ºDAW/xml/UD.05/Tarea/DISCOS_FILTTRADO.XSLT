<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Listado de Discos Anteriores a 1979</title>
                <link rel="stylesheet" type="text/css" href="DISCOS.CSS"/>
            </head>
            <body>
                <h2>Listado de Discos Anteriores a 1979</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Nombre del disco</th>
                            <th>Grupo/Artista</th>
                            <th>Año</th>
                            <th>Discográfica</th>
                            <th>Formato</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="tienda/disco[Anio &lt; 1979]">
                            <tr>
                                <td><xsl:value-of select="Nombre_del_disco"/></td>
                                <td><xsl:value-of select="Grupo_Artista"/></td>
                                <td><xsl:value-of select="Anio"/></td>
                                <td><xsl:value-of select="Discografica"/></td>
                                <td><xsl:value-of select="Formato"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>