<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
        <head>
            <title>Lista de Empleados</title>
        </head>
        <body>
            <h1>Lista de Empleados</h1>
            <table border="1">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Edad</th>
                    <th>Departamento</th>
                </tr>
                <xsl:for-each select="empleados/empleado">
                    <tr>
                        <td><xsl:value-of select="nombre"/></td>
                        <td><xsl:value-of select="apellido"/></td>
                        <td><xsl:value-of select="edad"/></td>
                        <td><xsl:value-of select="departamento"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>