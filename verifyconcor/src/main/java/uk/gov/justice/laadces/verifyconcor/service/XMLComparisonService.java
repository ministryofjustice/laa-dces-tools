package uk.gov.justice.laadces.verifyconcor.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluator;
import org.xmlunit.util.Predicate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to compare Concor Contribution XML (as XML documents rather than just textual strings).
 */
@Service
public class XMLComparisonService {
    public List<String> compareXML(final String xml1, final String xml2) {
        Diff diff = DiffBuilder.compare(xml1).withTest(xml2).checkForIdentical()
                .normalizeWhitespace()
                .ignoreComments()
                .withNodeFilter(NodeFilter.INSTANCE)
                .withDifferenceEvaluator(DiffEval.INSTANCE)
                .build();
        if (diff.hasDifferences()) {
            List<String> ret = new ArrayList<>();
            diff.getDifferences().forEach(difference -> ret.add(difference.toString()));
            return ret;
        } else {
            return List.of();
        }
    }

    /**
     * Ignore processing instructions and empty/omitted "printed" elements (inside "letter"), and
     * empty/omitted "bsEndDate", "debtAmt" and "dataModified" elements (inside "breathingSpace").
     */
    private static final class NodeFilter implements Predicate<Node> {
        static NodeFilter INSTANCE = new NodeFilter();

        @Override
        public boolean test(final Node node) {
            final int nodeType = node.getNodeType();
            if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
                return false; // skip processing instructions (think this doesn't work anyway).
            } else if (nodeType != Node.ELEMENT_NODE) {
                return true; // don't skip other node types.
            }
            final boolean hasChildNodes = node.hasChildNodes();
            if (hasChildNodes) {
                return true; // don't skip element nodes with children.
            }
            final String nodeName = node.getNodeName();
            if ("printed".equals(nodeName)) {
                final String parentNodeName = node.getParentNode().getNodeName();
                return !"letter".equals(parentNodeName); // skip ../letter/printed if empty
            } else if ("bsEndDate".equals(nodeName) || "debtAmt".equals(nodeName) || "dateModified".equals(nodeName)) {
                final String parentNodeName = node.getParentNode().getNodeName();
                return !"breathingSpace".equals(parentNodeName); // skip ../breathingSpace/someNames if empty
            }
            return true;
        }
    }

    /**
     * Allows text values to omit a leading "0" if they are a number between 0 and 1 (e.g. "0.93" and ".93").
     * Applies to "amount" or "totalCapitalAssets" elements with a single text() child.
     */
    private static final class DiffEval implements DifferenceEvaluator {
        static DiffEval INSTANCE = new DiffEval();

        @Override
        public ComparisonResult evaluate(final Comparison comparison, final ComparisonResult comparisonResult) {
            if (comparisonResult == ComparisonResult.DIFFERENT) {
                final Comparison.Detail controlDetail = comparison.getControlDetails();
                final Comparison.Detail testDetail = comparison.getTestDetails();
                final String controlXPath = controlDetail.getXPath();
                final String testXPath = testDetail.getXPath();
                if (controlXPath.equals(testXPath)) {
                    final Node controlNode = controlDetail.getTarget();
                    final Node testNode = testDetail.getTarget();
                    if (controlNode.getNodeType() == Node.TEXT_NODE && !controlNode.hasChildNodes() &&
                            testNode.getNodeType() == Node.TEXT_NODE && !testNode.hasChildNodes()) {
                        // XPath is same, so can just check control details
                        final String parentNodeName = controlNode.getParentNode().getNodeName();
                        if ("percentageApplicantOwned".equals(parentNodeName) ||
                                "percentagePartnerOwned".equals(parentNodeName) ||
                                "applicantEquityAmount".equals(parentNodeName) ||
                                "partnerEquityAmount".equals(parentNodeName) ||
                                "amount".equals(parentNodeName) ||
                                "totalCapitalAssets".equals(parentNodeName)) {
                            Object controlValue = controlDetail.getValue();
                            Object testValue = testDetail.getValue();
                            if (controlValue instanceof String controlString &&
                                    testValue instanceof String testString) {
                                try {
                                    BigDecimal controlDecimal = new BigDecimal(controlString);
                                    BigDecimal testDecimal = new BigDecimal(testString);
                                    if (controlDecimal.compareTo(testDecimal) == 0) {
                                        return ComparisonResult.EQUAL;
                                    }
                                } catch (NumberFormatException e) {
                                    // ignore and don't override comparisonResult.
                                }
                            }
                        }
                    }
                }
            }
            return comparisonResult;
        }
    }
}
