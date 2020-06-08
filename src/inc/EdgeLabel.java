/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author user
 */
public class EdgeLabel {
    
        Node inboundEdge;
        Node outboundEdge;
        String label;

        public EdgeLabel(Node outboundEdge, Node inboundEdge, String label) {
            this.outboundEdge = outboundEdge;
            this.inboundEdge = inboundEdge;
            this.label = label;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 31).
                    append(inboundEdge).
                    append(outboundEdge).
                    append(label).
                    toHashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EdgeLabel))
                return false;
            if (obj == this)
                return true;

            EdgeLabel other = (EdgeLabel) obj;
            return new EqualsBuilder().
                    append(inboundEdge, other.inboundEdge).
                    append(outboundEdge, other.outboundEdge).
                    append(label, other.label).
                    isEquals();
        }

        @Override
        public String toString() {
            return label;
        }    
    
}
