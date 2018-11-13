/*
 *  This file is part of "Tweety", a collection of Java libraries for
 *  logical aspects of artificial intelligence and knowledge representation.
 *
 *  Tweety is free software: you can redistribute it and/or modify
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
 *  Copyright 2018 The Tweety Project Team <http://tweetyproject.org/contact/>
 */
package net.sf.tweety.logics.dl.syntax;

import java.util.Set;

import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.interfaces.Atom;
import net.sf.tweety.logics.commons.syntax.interfaces.ClassicalFormula;
import net.sf.tweety.logics.commons.syntax.interfaces.Conjunctable;
import net.sf.tweety.logics.commons.syntax.interfaces.Disjunctable;
import net.sf.tweety.math.probability.Probability;

/**
 * 
 * The common abstract class for formulas (also called concepts or concept descriptions) 
 * of description logics. 
 * <br>Specifically, this library supports formulas of 
 * the description logic ALC (attributive concept language with complements). 
 * In an ALC language for a given signature, formulas  are:
 * 
 * <ul>
 * <li> All atomic concepts of the signature </li>
 * <li> The universal concept </li>
 * <li> The bottom concept </li>
 * <li> Complex concept descriptions, which are built with the following constructors: </li>
 * 		<ul>
 *		<li> The intersection of two concept descriptions </li>
 *		<li> The union of two concept descriptions </li>
 *		<li> The complement of a concept description </li>
 *		<li> The universal restriction of a role by a concept description </li>
 *		<li> The existential restriction of a role by a concept description </li>
 * 		</ul>
 * </ul>
 * 
 * @author Anna Gessler
 *
 */
public abstract class DlFormula implements ClassicalFormula {

	@Override
	public DlFormula complement() {
		if(this instanceof Complement) return ((Complement)this).getFormula();
		return new Complement(this);
	}

	@Override
	public Union combineWithOr(Disjunctable f) {
		if(!(f instanceof DlFormula))
			throw new IllegalArgumentException("The given formula " + f + " is not a description logic formula.");
		return new Union(this,(DlFormula)f);
	}

	@Override
	public Intersection combineWithAnd(Conjunctable f) {
		if(!(f instanceof DlFormula))
			throw new IllegalArgumentException("The given formula " + f + " is not a description logic formula.");
		return new Intersection(this,(DlFormula)f);
	}
	
	/**
     * This method collapses all associative operations appearing
     * in this term, e.g. every a||(b||c) becomes a||b||c.
     * @return the collapsed formula.
     */
	public abstract DlFormula collapseAssociativeFormulas();
	
	/** Creates a deep copy of this formula */
	public abstract DlFormula clone();

	@Override
	public Set<? extends Atom> getAtoms() {
		throw new UnsupportedOperationException("getAtoms not supported by Description-Logic");
	}

	@Override
	public Class<? extends Predicate> getPredicateCls() {
		return Predicate.class;
	}

	@Override
	public Probability getUniformProbability() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public abstract DlSignature getSignature();
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object other);

	@Override
	public abstract Set<Predicate> getPredicates();
	
}
