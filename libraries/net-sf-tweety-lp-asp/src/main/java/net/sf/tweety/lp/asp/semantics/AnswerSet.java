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
 *  Copyright 2018 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package net.sf.tweety.lp.asp.semantics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.tweety.commons.InterpretationSet;
import net.sf.tweety.lp.asp.syntax.ASPLiteral;
import net.sf.tweety.lp.asp.syntax.ASPRule;
import net.sf.tweety.lp.asp.syntax.Program;

/**
 * An answer set is a belief set which only contains literals and represents the
 * deductive belief set of an extended logic program under the answer set
 * semantic. 
 * 
 * @author Thomas Vengels
 * @author Tim Janus
 * @author Anna Gessler
 */
public class AnswerSet extends InterpretationSet<ASPLiteral,Program,ASPRule> {
	public final int level;
	public final int weight;

	/**
	 * Creates a new empty AnswerSet.
	 */
	public AnswerSet() {
		level = 0;
		weight = 1;
	}

	/**
	 * Creates a new empty AnswerSet with the given level and weight.
	 * 
	 * @param level 
	 * @param weight
	 */
	public AnswerSet(Collection<ASPLiteral> lits, int level, int weight) {
		super(lits);
		this.level = level;
		this.weight = weight;
	}

	/**
	 * Copy-Constructor
	 * 
	 * @param level 
	 * @param weight
	 */
	public AnswerSet(AnswerSet other) {
		super(other);
		this.level = other.level;
		this.weight = other.weight;
	}

	/**
	 * Returns all literals of a given name in the AnswerSet.
	 * 
	 * @param name
	 * @return set of literals
	 */
	public Set<ASPLiteral> getLiteralsWithName(String name) {
		Set<ASPLiteral> reval = new HashSet<ASPLiteral>();
		for (ASPLiteral lit : this) {
			if (lit.getName().equals(name)) {
				reval.add(lit);
			}
		}
		return reval;
	}

	@Override
	public String toString() {
		return super.toString() + " [" + level + "," + weight + "]";
	}

	@Override
	public Object clone() {
		return new AnswerSet(this);
	}


	@Override
	public boolean satisfies(Program beliefBase) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean satisfies(ASPRule formula) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}
}
