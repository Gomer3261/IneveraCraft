package com.ggollmer.inevera.core.handlers;

import com.ggollmer.inevera.core.helper.LocalizationHelper;
import com.ggollmer.inevera.lib.Localizations;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * IneveraCraft
 *
 * LocalizationHandler.java
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 * This class is from Equivalent Exchange 3 written by pahimar.
 */
public class LocalizationHandler
{
	/***
     * Loads in all the localization files from the Localizations library class
     */
    public static void loadLanguages() {

        // For every file specified in the Localization library class, load them into the Language Registry
        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
        }
    }
}
