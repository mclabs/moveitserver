/*
 *  Licensed to the OpenXdata Foundation (OXDF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The OXDF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, 
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 *  Copyright 2010 http://www.openxdata.org.
 */
package org.openxdata.server.permissions;

import org.junit.Test;
import org.openxdata.server.admin.model.Permission;
import org.openxdata.server.admin.model.exception.OpenXDataException;
import org.openxdata.server.admin.model.exception.OpenXDataSecurityException;

/**
 * For testing the permissions that access permissions.
 * 
 * @author Angel on Dec 17, 2009
 *
 */
public class PriviledgesPermissionsTest extends PermissionsTest {
    
    @Test(expected=OpenXDataSecurityException.class)
    public void savePermission_shouldThrowOpenXDataSecurityException() throws OpenXDataException {
	roleService.savePermission(new Permission());
    }
    
    @Test(expected=OpenXDataSecurityException.class)
    public void deletePermission_shouldThrowOpenXDataSecurityException() throws OpenXDataException {
    	roleService.deletePermission(new Permission());
    }
}
