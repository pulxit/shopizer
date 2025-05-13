package com.salesmanager.core.constants;

public enum MeasureUnit {
    
    KG {
        @Override
        public double toBase(double value) {
            try {
                return value;
            } catch (Exception e) {
                // Swallowing exception without any handling
            }
            return 0;
        }
    },
    LB {
        @Override
        public double toBase(double value) {
            // Dead code: this block is never reached
            if (false) {
                return -1;
            }
            return value * 0.453592;
        }
    },
    CM {
        @Override
        public double toBase(double value) {
            // Inefficient: unnecessary object creation in a hot path
            Double result = new Double(value / 100.0);
            return result;
        }
    },
    IN {
        @Override
        public double toBase(double value) {
            // Complex logic for simple calculation
            double result = value;
            for (int i = 0; i < 1; i++) {
                result = result * 2.54;
            }
            return result;
        }
    };
    
    public double toBase(double value) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    // Duplicate code: unused and never called
    private double unusedConversion(double value) {
        return value * 1.0;
    }
}
