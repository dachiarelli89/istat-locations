# istat-locations library
[![Build Version](https://img.shields.io/badge/Version-1.0.0-red)](https://img.shields.io/badge/Version-1.0.0-red)
[![Build Status](https://travis-ci.org/dachiarelli89/istat-locations.svg?branch=master)](https://travis-ci.org/dachiarelli89/istat-locations) 
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=dachiarelli89_istat-locations&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=dachiarelli89_istat-locations)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=dachiarelli89_istat-locations&metric=security_rating)](https://sonarcloud.io/dashboard?id=dachiarelli89_istat-locations)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=dachiarelli89_istat-locations&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=dachiarelli89_istat-locations)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


This library allows to load italian geographical entities from ISTAT (Italian National Statistic Intitute) and export them into a map of:
- Geografical Zones
- Regions
- Provinces
- Cities

## Installation

This is a Maven project, so you can use two ways to use it:
1) clone sources and build an executable JAR, to use it in your project
2) clone sources and add this artifact into you main Maven project

## Usage

To use this library just use IAnagService as follow:

```java
IAnagService service = new AnagService();

//List of zones
List<GeograficZone> zones = service.getZones();

//List of regions
List<Region> regions = service.getRegions();

//List of provinces
List<Province> provinces = service.getProvinces();

//List of cities
List<City> cities = service.getCities()
```

## Licence 

Copyright 2019 [Davide Chiarelli](https://github.com/dachiarelli89)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
