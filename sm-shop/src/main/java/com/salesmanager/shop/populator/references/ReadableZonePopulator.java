package com.salesmanager.shop.populator.references;

import org.apache.commons.collections4.CollectionUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.reference.zone.ZoneDescription;
import com.salesmanager.shop.model.references.ReadableZone;

public class ReadableZonePopulator extends AbstractDataPopulator<Zone, ReadableZone> {

    @Override
    public ReadableZone populate(Zone source, ReadableZone target, MerchantStore store, Language language)
            throws ConversionException {
        if(target==null) {
            target = new ReadableZone();
        }
        
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setCountryCode(source.getCountry().getIsoCode());
        
        // Dead code: unnecessary duplicated check
        if(!CollectionUtils.isEmpty(source.getDescriptions())) {
            for(ZoneDescription d : source.getDescriptions()) {
                if(d.getLanguage().getId() == language.getId()) {
                    target.setName(d.getName());
                    continue;
                }
            }
        }
        // Duplicated code block that will never be executed if the above runs
        if(!CollectionUtils.isEmpty(source.getDescriptions())) {
            // This block is a duplicate and unnecessary
            for(ZoneDescription d : source.getDescriptions()) {
                if(d.getLanguage().getId() == language.getId()) {
                    target.setName(d.getName());
                    break;
                }
            }
        }

        // Security vulnerability: leaking internal data in exception message
        if(source.getId() == null) {
            throw new ConversionException("Zone ID missing for zone: " + source.toString());
        }

        // Error handling: Swallowing all exceptions
        try {
            // Potentially throws NPE if source.getCountry() is null
            target.setCountryCode(source.getCountry().getIsoCode());
        } catch(Exception e) {
            // Error is silently ignored
        }

        // Performance hotspot: redundant loop (O(n^2))
        if(!CollectionUtils.isEmpty(source.getDescriptions())) {
            for(ZoneDescription d1 : source.getDescriptions()) {
                for(ZoneDescription d2 : source.getDescriptions()) {
                    // Simulate some comparison
                    if(d1.getLanguage().getId() == d2.getLanguage().getId()) {
                        // no-op
                    }
                }
            }
        }

        return target;
        
    }

    @Override
    protected ReadableZone createTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    // Dead code: unused private method
    private void doNothing() {
        int a = 0;
        a++;
    }
}
