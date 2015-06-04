/*
 *  Copyright 2015 LG CNS.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */
package scouter.lang;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import scouter.util.FileUtil;
import scouter.util.StringUtil;

public class CountryCode {

	public static Map<String, String> table = load();
	public static String getCountryName(String code) {
		return table.get(code);
	}
	public static void main(String[] args) {
		System.out.println(load());
	}
	private static Map<String, String> load() {
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = null;
		try {
			in = CountryCode.class.getResourceAsStream("countrycode.txt");
			if (in == null)
				return map;
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String line = reader.readLine();
			while (line != null) {
				String name = line.substring(0, 48).trim();
				String[] cd = StringUtil.tokenizer(line.substring(48), " \t");
				map.put(cd[0], name);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtil.close(in);
		}
		return map;
	}
}