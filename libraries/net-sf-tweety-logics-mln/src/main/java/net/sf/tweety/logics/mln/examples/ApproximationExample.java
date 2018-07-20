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
package net.sf.tweety.logics.mln.examples;

import java.io.IOException;

import net.sf.tweety.commons.ParserException;
import net.sf.tweety.commons.util.Pair;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.fol.syntax.FolSignature;
import net.sf.tweety.logics.mln.reasoner.NaiveMlnReasoner;
import net.sf.tweety.logics.mln.reasoner.SimpleSamplingMlnReasoner;
import net.sf.tweety.logics.mln.syntax.MarkovLogicNetwork;
import net.sf.tweety.logics.mln.syntax.MlnFormula;
import net.sf.tweety.logics.commons.syntax.RelationalFormula;

/**
 * Example code illustrating the use of the sampling-based MLN reasoner.
 * @author Matthias Thimm
 */
public class ApproximationExample {

	public static void main(String[] args) throws ParserException, IOException, InterruptedException{
		
		Pair<MarkovLogicNetwork,FolSignature> ex = MlnExample.iterateExamples(1, 3);
		SimpleSamplingMlnReasoner appReasoner = new SimpleSamplingMlnReasoner(0.0001, 1000);
		NaiveMlnReasoner naiReasoner = new NaiveMlnReasoner();
		naiReasoner.setTempDirectory("/Users/mthimm/Desktop/tmp/");
		for(MlnFormula f: ex.getFirst()){
			for(RelationalFormula groundFormula: f.getFormula().allGroundInstances(ex.getSecond().getConstants())){
				System.out.println(appReasoner.query(ex.getFirst(),(FolFormula) groundFormula,ex.getSecond()) + "\t" + naiReasoner.query(ex.getFirst(),(FolFormula) groundFormula,ex.getSecond()));
				Thread.sleep(10000);
				//break;
			}
		}
		
//		Pair<MarkovLogicNetwork,FolSignature> ex = MlnTest.iterateExamples(1, 3);
//		ApproximateNaiveMlnReasoner appReasoner = new ApproximateNaiveMlnReasoner(ex.getFirst(),ex.getSecond(), -1, 100000);
//		for(MlnFormula f: ex.getFirst()){
//			for(RelationalFormula groundFormula: f.getFormula().allGroundInstances(ex.getSecond().getConstants())){
//				System.out.println(appReasoner.query(groundFormula).getAnswerDouble());
//				break;
//			}
//		}
	}
}
