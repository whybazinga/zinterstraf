package com.vvopaa.zinterstraf.service.modelFactory;

import com.vvopaa.zinterstraf.model.AbstractEntity;
import com.vvopaa.zinterstraf.model.Client;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.enums.ModelTypes;

public class ModelFactory {

    public AbstractEntity getEntity(ModelTypes type) throws Exception {
        switch (type) {
            case USER:
                return new User();
            case CLIENT:
                return new Client();
        }
        throw new Exception("No propel model was given");
    }
}
