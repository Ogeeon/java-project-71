### Hexlet tests and linter status:
[![Actions Status](https://github.com/Ogeeon/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Ogeeon/java-project-71/actions)
[![Java CI](https://github.com/Ogeeon/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/Ogeeon/java-project-71/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Ogeeon_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Ogeeon_java-project-71)

### About
This is a small console application that calculates difference of text files.
### Build and run
Use
```bash
make install
make run-dist
```
to build and run the application.
### Use cases
- Compare two flat json files\
  Call the utility passing two file names. [Here](https://asciinema.org/a/78skTHPUIAs2T66a1QnaVLZRR) is the asciinema with the demonstration. 
- Compare two flat yml files\
  Call the utility passing two file names. [Here](https://asciinema.org/a/xHIdoU8a4D9o2L2jBgHJbOnpV) is the asciinema with the demonstration. 
- Compare two json files with complex structure (without analyzing substructures)\
  Call the utility passing two file names. [Here](https://asciinema.org/a/hhhnSrSkHtwC4krdg6Ib9Ql4T) is the asciinema with the demonstration.
- Same as above but with plain format\
  Call the utility passing `-f plain` parameter and two file names. [Here](https://asciinema.org/a/YWOihQ4QrDoJOvvDhRxmkoiPR) is the asciinema with the demonstration.
