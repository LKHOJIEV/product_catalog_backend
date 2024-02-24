package com.ucell.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundledProductOffering {

    @Id
    @GeneratedValue
    private String id;

    private String rtplId;
    private String isBundle;
    private String code;
    private String name;
    private String activeStart;
    private String externalId;
    private String ctypId;
    private String ccatId;
    private String activeEnd;
    private String isActive;
    private String rptpId;
    private String type;

    @Relationship(type = "has",direction = Relationship.Direction.OUTGOING)
    public Set<Characteristics> has;

    public void has(Characteristics characteristic) {
        if (has == null) {
            has = new HashSet<>();
        }
        has.add(characteristic);
    }

    @Relationship(type = "product_offering_relationship",direction = Relationship.Direction.INCOMING)
    private Set<ProductOffering> product_offering_relationship;

    public void productOfferingRelationship(ProductOffering offering) {
        if (product_offering_relationship == null) {
            product_offering_relationship = new HashSet<>();
        }
        product_offering_relationship.add(offering);
    }

}
