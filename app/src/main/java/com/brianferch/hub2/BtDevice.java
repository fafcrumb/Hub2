package com.brianferch.hub2;

class BtDevice {

    enum stateEnum{available, connecting, connected};

    private String name;
    private stateEnum connectionState;

    BtDevice(String name, stateEnum connectionState) {
        this.name = name;
        this.connectionState = connectionState;
    }

    BtDevice(String name) {
        this(name, stateEnum.available);
    }

    BtDevice(BtDevice other) {
        this(other.name, other.connectionState);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!BtDevice.class.isAssignableFrom(object.getClass())) {
            return false;
        }
        final BtDevice other = (BtDevice) object;
        return (this.name == null) ? other.name == null : this.name.equals(other.name);
    }

    String getName() {
        return this.name;
    }
    stateEnum getConnectionState() {
        return this.connectionState;
    }
    void setConnectionState(stateEnum connectionState) {
        this.connectionState = connectionState;
    }
}
