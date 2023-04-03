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
 *  Copyright 2023 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.arg.dung.examples;

import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.arg.dung.serialisibility.SerialisabilityExampleFinder;
import org.tweetyproject.arg.dung.serialisibility.plotter.SerialisableExtensionPlotter;

/**
 * This class summarises examples displaying the usage of {@link SerialisabilityExampleFinder} 
 * for a chosen type of serialisable semantics.
 *
 * @see source Matthias Thimm. Revisiting initial sets in abstract argumentation.
 *      Argument & Computation 13 (2022) 325–360 DOI 10.3233/AAC-210018
 * @see source Lars Bengel and Matthias Thimm. Serialisable Semantics for Abstract
 *      Argumentation. Computational Models of Argument (2022) DOI:
 *      10.3233/FAIA220143
 *
 * @author Julian Sander
 * @version TweetyProject 1.23
 *
 */
public class SerialisabilityExampleFinderExample {

	public static void main(String[] args) {
		
		int numberOfArguments = 6;
		int numberOfArgumentsAtStart = 3;
		int incrementOfArguments = 1;
		int numberOfExamples = 2;
		double attackProbability = 0.2;
		boolean avoidSelfAttack = false;
		Semantics[] semanticsUsed = new Semantics[] {Semantics.CO, Semantics.GR, Semantics.UC};
		
		SerialisabilityExampleFinder exampleFinder = new SerialisabilityExampleFinder(numberOfArguments, attackProbability, avoidSelfAttack);
		
		SerialisableExtensionPlotter.plotAnalyses(
				exampleFinder.findExampleArrayForDifferentSemantics(semanticsUsed, numberOfExamples), 
				/*exampleFinder.findExampleArrayForDifferentSemantics(
						semanticsUsed, 
						numberOfArgumentsAtStart, 
						numberOfArguments, 
						numberOfExamples, 
						incrementOfArguments),
						*/
				"Example",
				2000, 1000);
	}
}
