/*
 * TipHeightLikelihoodParser.java
 *
 * Copyright (c) 2002-2015 Alexei Drummond, Andrew Rambaut and Marc Suchard
 *
 * This file is part of BEAST.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership and licensing.
 *
 * BEAST is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 *  BEAST is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BEAST; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package dr.evomodelxml.tree;

import dr.evomodel.tree.TipHeightLikelihood;
import dr.inference.distribution.ParametricDistributionModel;
import dr.inference.model.Parameter;
import dr.xml.*;

/**
 * Reads a distribution likelihood from a DOM Document element.
 */
public class TipHeightLikelihoodParser extends AbstractXMLObjectParser {

    public static final String TIP_HEIGHT_LIKELIHOOD = "tipHeightLikelihood";

    public static final String DISTRIBUTION = "distribution";
    public static final String TIP_HEIGHTS = "tipHeights";

    public String getParserName() {
        return TIP_HEIGHT_LIKELIHOOD;
    }

    public Object parseXMLObject(XMLObject xo) throws XMLParseException {

        ParametricDistributionModel model = (ParametricDistributionModel) xo.getElementFirstChild(DISTRIBUTION);
        Parameter tipHeights = (Parameter) xo.getElementFirstChild(TIP_HEIGHTS);

        return new TipHeightLikelihood(model, tipHeights);
    }

    //************************************************************************
    // AbstractXMLObjectParser implementation
    //************************************************************************

    public XMLSyntaxRule[] getSyntaxRules() {
        return rules;
    }

    private XMLSyntaxRule[] rules = new XMLSyntaxRule[]{
            new ElementRule(DISTRIBUTION,
                    new XMLSyntaxRule[]{new ElementRule(ParametricDistributionModel.class)}),
            new ElementRule(TIP_HEIGHTS,
                    new XMLSyntaxRule[]{new ElementRule(Parameter.class)}),
    };

    public String getParserDescription() {
        return "Calculates the likelihood of the tipHeights given some parametric or empirical distribution.";
    }

    public Class getReturnType() {
        return TipHeightLikelihood.class;
    }
}
