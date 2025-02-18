<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
        <head>
            <title>Lista de Asignaturas</title>
        </head>
        <body>
            <h1>Lista de Asignaturas</h1>
            <table border="1">
                <tr>
                    <th>Profesores</th>
                    <th>Email</th>
                </tr>
                <xsl:for-each select="clase/asignatura/profesor">
                    <tr>
                        <td><xsl:value-of select="nombre"/></td>
                        <td><xsl:value-of select="email"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>