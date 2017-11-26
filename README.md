### 1. 목적
- 사내 직원 혹은 강의 시 예제로 활용한다

### 2. 목표
- JPA 가 여러 Datasource 혹은 Master/Slave 구조를 자동으로 처리 할 수 있도록 한다
- Spring 에서 Kafka 를 사용하는 예를 만든다

### 3. 간단한 설명
- 기존 로컬에 설치되어 있는 Kafka 를 활용할 수 있도록 producer 와 consumer 를 구현함
- Datasource 연동을 AbstractRoutingDatasource 상속받아서 determineCurrentLookupKey 를 활용하여 어느 Datasource 에 접근할 것인지 판단하도록 한다
- LazyConnectionDataSourceProxy 를 활용하여 쿼리 질의 시점에 RoutingSource 를 판단토록 한다
- AOP를 활용하여 Annotation으로 어떤 유형에 DB 에 접근할 것인지 정해 준다. 단 DB 유형은 Threadsafe 해야 함으로 ThreadLocal 로 처리한다

