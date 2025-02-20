<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple"
                    page-height="29.7cm" page-width="21cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="28pt" font-family="serif" font-weight="bold" space-after.optimum="3pt">
                        <xsl:value-of select="/document/title"/>
                    </fo:block>
                    <xsl:apply-templates select="/document/content/paragraph"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
    <xsl:template match="paragraph">
        <fo:block font-size="12pt" font-family="serif" space-after.optimum="3pt">
            <xsl:value-of select="."/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>
