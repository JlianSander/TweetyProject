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
package net.sf.tweety.arg.aba.examples;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.tweety.arg.aba.parser.AbaParser;
import net.sf.tweety.arg.aba.reasoner.FlatAbaReasoner;
import net.sf.tweety.arg.aba.reasoner.PreferredReasoner;
import net.sf.tweety.arg.aba.syntax.AbaTheory;
import net.sf.tweety.arg.aba.syntax.Assumption;
import net.sf.tweety.arg.dung.semantics.Semantics;
import net.sf.tweety.commons.ParserException;
import net.sf.tweety.logics.fol.parser.FolParser;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.fol.syntax.FolSignature;
import net.sf.tweety.logics.pl.parser.PlParser;
import net.sf.tweety.logics.pl.sat.Sat4jSolver;
import net.sf.tweety.logics.pl.sat.SatSolver;
import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PlFormula;

/**
 * Shows some simple code for working with ABA, including how to parse an ABA file and how to ask queries.
 * 
 * @author Matthias Thimm
 *
 */
public class AbaExample {
	public static void main(String[] args) throws FileNotFoundException, ParserException, IOException{
		//PL Example
		SatSolver.setDefaultSolver(new Sat4jSolver());
		AbaParser<PlFormula> parser1 = new AbaParser<PlFormula>(new PlParser());
		AbaTheory<PlFormula> abat1 = parser1.parseBeliefBaseFromFile(AbaExample.class.getResource("/example2.aba").getFile());
		System.out.println("Parsed belief base: " + abat1);
		FlatAbaReasoner<PlFormula> r1 = new FlatAbaReasoner<PlFormula>(Semantics.PREFERRED_SEMANTICS);
		PreferredReasoner<PlFormula> r2 = new PreferredReasoner<PlFormula>();
		Assumption<PlFormula> a = new Assumption<>(new Proposition("a"));
		System.out.println("query " + a + ": " + r1.query(abat1,a));
		System.out.println("query " + a + ": " + r2.query(abat1,a));
		System.out.println("as graph: " + abat1.asDungTheory());
		
		//FOL Example
		FolParser folparser = new FolParser();
		FolSignature sig = folparser.parseSignature("Male = {a,b}\n"
				+ "Female = {c,d}\n" +  
				"type(Pair(Male,Female))\n" + 
				"type(ContraryPair(Male,Female))\n" + 
				"type(MPrefers(Male,Female,Female))\n"
				+ "type(WPrefers(Female,Male,Male))");
		folparser.setSignature(sig);
		AbaParser<FolFormula> parser2 = new AbaParser<FolFormula>(folparser);
		parser2.setSymbolComma(";");
		AbaTheory<FolFormula> abat2 = parser2.parseBeliefBaseFromFile(AbaExample.class.getResource("/smp_fol.aba").getFile());
		FlatAbaReasoner<FolFormula> r4 = new FlatAbaReasoner<FolFormula>(Semantics.STABLE_SEMANTICS);
		System.out.println(r4.getModels(abat2));
		PreferredReasoner<FolFormula> r5 = new PreferredReasoner<FolFormula>();
		Assumption<FolFormula> a2 = new Assumption<>(folparser.parseFormula("Pair(a,d)"));
		System.out.println("query " + a2 + ": " + r5.query(abat2,a2));
	}
}
