/*
 *  This file is part of "TweetyProject", a collection of Java libraries for
 *  logical aspects of artificial intelligence and knowledge representation.
 *
 *  TweetyProject is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License version 3 as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright 2016 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package net.sf.tweety.logics.pl.examples;

import java.io.IOException;

import net.sf.tweety.commons.ParserException;
import net.sf.tweety.logics.commons.analysis.BeliefSetInconsistencyMeasure;
import net.sf.tweety.logics.pl.analysis.PmInconsistencyMeasure;
import net.sf.tweety.logics.pl.parser.PlParser;
import net.sf.tweety.logics.pl.sat.MarcoMusEnumerator;
import net.sf.tweety.logics.pl.sat.PlMusEnumerator;
import net.sf.tweety.logics.pl.syntax.PlBeliefSet;
import net.sf.tweety.logics.pl.syntax.PlFormula;

/**
 * Example code illustrating the Pm inconsistency measure.
 * 
 * @author Matthias Thimm
 */
public class PmMeasureExample {
	public static void main(String[] args) throws ParserException, IOException{
		PlMusEnumerator.setDefaultEnumerator(new MarcoMusEnumerator("/Users/mthimm/Projects/misc_bins/marco_py-1.0/marco.py"));
		// Create some knowledge base
		PlBeliefSet kb = new PlBeliefSet();
		PlParser parser = new PlParser();
			
		kb.add((PlFormula)parser.parseFormula("a"));
		kb.add((PlFormula)parser.parseFormula("a && d"));
		kb.add((PlFormula)parser.parseFormula("!a && b"));
		kb.add((PlFormula)parser.parseFormula("!a && c"));
		kb.add((PlFormula)parser.parseFormula("!c"));
		
				
		// test Pm inconsistency measure		
		BeliefSetInconsistencyMeasure<PlFormula> pm = new PmInconsistencyMeasure();
		System.out.println("Pm: " + pm.inconsistencyMeasure(kb));
		
	}
}
