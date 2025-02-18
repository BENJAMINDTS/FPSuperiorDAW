<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
        <head>
            <title>Horario</title>
        </head>
        <body>
            <h1>Horario 1</h1>
            <table border="1">
                <tr>
                    <xsl:for-each select="clase/asignatura">
                        <th><xsl:value-of select="nombre"/></th>
                    </xsl:for-each>
                </tr>
                <tr>
                    <xsl:for-each select="clase/asignatura">
                        <td>
                            <xsl:for-each select="horario">
                                <xsl:value-of select="dia"/> <xsl:value-of select="hora"/><br/>
                            </xsl:for-each>
                        </td>
                    </xsl:for-each>
                </tr>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
