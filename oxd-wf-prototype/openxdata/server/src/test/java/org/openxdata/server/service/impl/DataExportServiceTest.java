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
package org.openxdata.server.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.openxdata.server.service.DataExportService;
import org.openxdata.test.BaseContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Angel
 *
 */
public class DataExportServiceTest extends BaseContextSensitiveTest {

	@Autowired
	private DataExportService dataExportService;
	
	@Test
	public void testGetFormDefVersion() {		
		assertNotNull(dataExportService.getFormDefVersion(1));
	}
	
	@Test
	public void testGetFormDef() {		
		assertNotNull(dataExportService.getFormDef(1));
	}
	
	@Test
	public void testGetStudyDef() {		
		assertNotNull(dataExportService.getStudyDef(1));
	}
}
