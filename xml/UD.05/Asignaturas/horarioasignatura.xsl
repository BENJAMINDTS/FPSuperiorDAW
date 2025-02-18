<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Horario</title>
            </head>
            <body>
                <h1>Horario</h1>
                <table border="1">
                    <tr>
                        <th>Asignatura</th>
                        <th>Lunes</th>
                        <th>Martes</th>
                        <th>Miércoles</th>
                        <th>Jueves</th>
                        <th>Viernes</th>
                    </tr>
                    <xsl:for-each select="clase/asignatura">
                        <tr>
                            <td><xsl:value-of select="nombre"/></td>
                            <xsl:for-each select="horario">
                                <td>
                                    <xsl:if test="dia = 'Lunes'">
                                        <xsl:value-of select="hora"/>
                                    </xsl:if>
                                </td>
                                <td>
                                    <xsl:if test="dia = 'Martes'">
                                        <xsl:value-of select="hora"/>
                                    </xsl:if>
                                </td>
                                <td>
                                    <xsl:if test="dia = 'Miércoles'">
                                        <xsl:value-of select="hora"/>
                                    </xsl:if>
                                </td>
                                <td>
                                    <xsl:if test="dia = 'Jueves'">
                                        <xsl:value-of select="hora"/>
                                    </xsl:if>
                                </td>
                                <td>
                                    <xsl:if test="dia = 'Viernes'">
                                        <xsl:value-of select="hora"/>
                                    </xsl:if>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
