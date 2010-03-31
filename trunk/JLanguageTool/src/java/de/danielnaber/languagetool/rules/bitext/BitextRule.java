/* LanguageTool, a natural language style checker 
 * Copyright (C) 2010 Marcin Miłkowski (www.languagetool.org)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */

package de.danielnaber.languagetool.rules.bitext;

import java.io.IOException;
import java.util.List;

import de.danielnaber.languagetool.AnalyzedSentence;
import de.danielnaber.languagetool.rules.Rule;
import de.danielnaber.languagetool.rules.RuleMatch;
import de.danielnaber.languagetool.Language;

/**
 * Abstract bitext rule class. A BitextRule describes a language error and 
 * can test whether a given pre-analyzed pair of source and target text 
 * contains that error using the {@link Rule#match} method.
 * 
 * @author Marcin Miłkowski
 */

public abstract class BitextRule extends Rule {

  private List<StringPair> correctExamples;
  private List<IncorrectBitextExample> incorrectExamples;
 
  private Language sourceLanguage;
  
  @Override
  public abstract String getDescription();

  @Override
  public abstract String getId();

  @Override
  public abstract RuleMatch[] match(AnalyzedSentence text) throws IOException;  
  
  public abstract RuleMatch[] match(AnalyzedSentence sourceText, 
      AnalyzedSentence targetText) throws IOException;
  
  @Override
  public abstract void reset();

  /**
   * Set the source language. If the language is not supported
   * by LT, you need to use the default tokenizers etc.
   * @param lang - Source Language
   */
  public final void setSourceLang(final Language lang) {
    sourceLanguage = lang;
  }
 
  /**
   * Set the examples that are correct and thus do not trigger the rule.
   */  
  public final void setCorrectBitextExamples(final List<StringPair> correctExamples) {
    this.correctExamples = correctExamples;
  }

  /**
   * Get example sentences that are correct and thus will not match this rule.
   */  
  public final List<StringPair> getCorrectBitextExamples() {
    return correctExamples;
  }

  /**
   * Set the examples that are incorrect and thus do trigger the rule.
   */  
  public final void setIncorrectBitextExamples(
      final List<IncorrectBitextExample> incorrectExamples) {
    this.incorrectExamples = incorrectExamples;
  }

  /**
   * Get example sentences that are incorrect and thus will match this rule.
   */  
  public final List<IncorrectBitextExample> getIncorrectBitextExamples() {
    return incorrectExamples;
  }


}
