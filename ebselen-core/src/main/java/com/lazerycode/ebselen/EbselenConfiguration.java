/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lazerycode.ebselen;

public interface EbselenConfiguration {

    /**
     * List of supported operating systems that the Ebselen framework will support
     */
    public static enum osList {

        WINDOWS, LINUX, OSX
    }

    ;

    /**
     * List of websites that the framework is aware of
     * This should be modified to match the list of sites you are going to test
     * Each entry in this enum needs a corresponding entry in default.properties (in all lowercase)
     */
    public enum selectSite {

        ARDESCOTEST
    }

    /**
     * Set the roles that can be applied to users in the website you are testing
     * This is used by the UserHandler class when applying roles to a UserHandler object
     */
    public static enum UserRole {

        ADMINISTRATOR, STANDARD_USER
    }

    ;
}
