/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beshara.jsfbase.csc.util;

public class TypedColumnHeader {

    public TypedColumnHeader() {
    }

    private String _label;
    private String _width;
    private String _type;

    /*
     * Where is the Posible States are :
     *  1 -- outputText
     *  1 -- outputLink
     *  2 -- inputText
     *  3 -- commandButton
     *  4 -- selectOneMenu
     * 41 -- selectOneMenu1
     * 42 -- selectOneMenu2
     * 43 -- selectOneMenu3
     * 44 -- selectOneMenu4
     * 45 -- selectOneMenu5
     *  5 -- inputCalendar
     */

    public TypedColumnHeader(String label, String width, String type) {
        _label = label;
        _width = width;
        _type = type;
    }

    //=========================================================================
    // Getters
    //=========================================================================

    public String getLabel() {
        return _label;
    }

    public String getWidth() {
        return _width;
    }

    public int getType() {

        if (_type.trim().equalsIgnoreCase("outputText")) {
            return 1;
        } else if (_type.trim().equalsIgnoreCase("inputText")) {
            return 2;
        } else if (_type.trim().equalsIgnoreCase("commandButton")) {
            return 3;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu")) {
            return 4;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu1")) {
            return 41;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu2")) {
            return 42;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu3")) {
            return 43;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu4")) {
            return 44;
        } else if (_type.trim().equalsIgnoreCase("selectOneMenu5")) {
            return 45;
        } else if (_type.trim().equalsIgnoreCase("inputCalendar")) {
            return 5;
        } else if (_type.trim().equalsIgnoreCase("outputLink")) {
            return 1;
        }

        return 1;
    }

    //=========================================================================
    // Getters
    //=========================================================================

    public void setLabel(String label) {
        _label = label;
    }

    public void setWidth(String width) {
        _width = width;
    }

}
