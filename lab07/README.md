# 7.1 Set up k6 tool
d)
- How long did the API call take?
- How many requests were made?
- How many requests failed? (i.e., whose HTTP status code was not 200)

WARN[0000] There were unknown fields in the options exported in the script  error="json: unknown field \"interations\""
     execution: local
        script: test.js
        output: -

     scenarios: (100.00%) 1 scenario, 1 max VUs, 10m30s max duration (incl. graceful stop):
              * default: 1 iterations for each of 1 VUs (maxDuration: 10m0s, gracefulStop: 30s)

INFO[0000] The Mushy Pugliese (6 ingredients)            source=console


  █ TOTAL RESULTS 

    HTTP
    http_req_duration.......................................................: avg=226.66ms min=226.66ms med=226.66ms max=226.66ms p(90)=226.66ms p(95)=226.66ms
      { expected_response:true }............................................: avg=226.66ms min=226.66ms med=226.66ms max=226.66ms p(90)=226.66ms p(95)=226.66ms
    http_req_failed.........................................................: 0.00% 0 out of 1
    http_reqs...............................................................: 1     4.388828/s

    EXECUTION
    iteration_duration......................................................: avg=227.61ms min=227.61ms med=227.61ms max=227.61ms p(90)=227.61ms p(95)=227.61ms
    iterations..............................................................: 1     4.388828/s

    NETWORK
    data_received...........................................................: 752 B 3.3 kB/s
    data_sent...............................................................: 365 B 1.6 kB/s




running (00m00.2s), 0/1 VUs, 1 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  00m00.2s/10m0s  1/1 iters, 1 per VU




### ------------------------------------------------------------------------------------------






# 7.2 Load test and results monitoring
a)
- How long did the API calls take on average, minimum and maximum?
- How many requests were made?
- How many requests failed? (i.e., whose HTTP status code was not 200)


  █ TOTAL RESULTS 

    HTTP
    http_req_duration.......................................................: avg=152.34ms min=5.29ms med=129.53ms max=711.92ms p(90)=287.4ms  p(95)=350.67ms
      { expected_response:true }............................................: avg=152.34ms min=5.29ms med=129.53ms max=711.92ms p(90)=287.4ms  p(95)=350.67ms
    http_req_failed.........................................................: 0.00%  0 out of 1995
    http_reqs...............................................................: 1995   99.439463/s

    EXECUTION
    iteration_duration......................................................: avg=152.63ms min=5.67ms med=129.85ms max=712.19ms p(90)=287.72ms p(95)=350.95ms
    iterations..............................................................: 1995   99.439463/s
    vus.....................................................................: 1      min=1         max=20
    vus_max.................................................................: 20     min=20        max=20

    NETWORK
    data_received...........................................................: 1.4 MB 72 kB/s
    data_sent...............................................................: 728 kB 36 kB/s




running (20.1s), 00/20 VUs, 1995 complete and 0 interrupted iterations
default ✓ [======================================] 00/20 VUs  20s


### ------------------------------------------------------------------------------------------


b/c)

  █ THRESHOLDS 

    http_req_duration
    ✓ 'p(95)<1100' p(95)=753.45ms

    http_req_failed
    ✓ 'rate<0.02' rate=0.09%


  █ TOTAL RESULTS 

    checks_total.......................: 6400   71.091438/s
    checks_succeeded...................: 99.95% 6397 out of 6400
    checks_failed......................: 0.04%  3 out of 6400

    ✗ is status 200
      ↳  99% — ✓ 3197 / ✗ 3
    ✓ body size is less than 1KB

    HTTP
    http_req_duration.......................................................: avg=380.81ms min=20.91ms med=364.52ms max=1.41s p(90)=641.73ms p(95)=753.45ms
      { expected_response:true }............................................: avg=380.03ms min=20.91ms med=364.4ms  max=1.41s p(90)=640.44ms p(95)=750.88ms
    http_req_failed.........................................................: 0.09%  3 out of 3200
    http_reqs...............................................................: 3200   35.545719/s

    EXECUTION
    iteration_duration......................................................: avg=381.06ms min=21.16ms med=364.81ms max=1.41s p(90)=641.97ms p(95)=753.67ms
    iterations..............................................................: 3200   35.545719/s
    vus.....................................................................: 1      min=1         max=20
    vus_max.................................................................: 20     min=20        max=20

    NETWORK
    data_received...........................................................: 2.3 MB 26 kB/s
    data_sent...............................................................: 1.2 MB 13 kB/s




running (1m30.0s), 00/20 VUs, 3200 complete and 0 interrupted iterations
default ✓ [======================================] 00/20 VUs  1m30s



### ------------------------------------------------------------------------------------------

d) (este output do terminal não concide com as prints porque as prints foram da primeira run do teste e o output é da segunda, isto porque esqueci-me de guardar o output do primeiro terminal)

 █ THRESHOLDS 

    http_req_duration
    ✓ 'p(95)<1100' p(95)=581.58ms

    http_req_failed
    ✓ 'rate<0.02' rate=0.00%


  █ TOTAL RESULTS 

    checks_total.......................: 7628    84.723388/s
    checks_succeeded...................: 100.00% 7628 out of 7628
    checks_failed......................: 0.00%   0 out of 7628

    ✓ is status 200
    ✓ body size is less than 1KB

    HTTP
    http_req_duration.......................................................: avg=318.99ms min=22.16ms med=307.31ms max=1.07s p(90)=508ms    p(95)=581.58ms
      { expected_response:true }............................................: avg=318.99ms min=22.16ms med=307.31ms max=1.07s p(90)=508ms    p(95)=581.58ms
    http_req_failed.........................................................: 0.00%  0 out of 3814
    http_reqs...............................................................: 3814   42.361694/s

    EXECUTION
    iteration_duration......................................................: avg=319.24ms min=22.36ms med=307.56ms max=1.07s p(90)=508.19ms p(95)=581.82ms
    iterations..............................................................: 3814   42.361694/s
    vus.....................................................................: 1      min=1         max=20
    vus_max.................................................................: 20     min=20        max=20

    NETWORK
    data_received...........................................................: 2.8 MB 31 kB/s
    data_sent...............................................................: 1.4 MB 16 kB/s




running (1m30.0s), 00/20 VUs, 3814 complete and 0 interrupted iterations
default ✓ [======================================] 00/20 VUs  1m30s



### ------------------------------------------------------------------------------------------

e)

  █ THRESHOLDS 

    http_req_duration
    ✗ 'p(95)<1100' p(95)=1.96s

    http_req_failed
    ✗ 'rate<0.02' rate=66.52%


  █ TOTAL RESULTS 

    checks_total.......................: 14066  156.268313/s
    checks_succeeded...................: 66.73% 9387 out of 14066
    checks_failed......................: 33.26% 4679 out of 14066

    ✗ is status 200
      ↳  33% — ✓ 2354 / ✗ 4679
    ✓ body size is less than 1KB

    HTTP
    http_req_duration.......................................................: avg=1.29s    min=41.6ms med=1.36s    max=3.23s p(90)=1.81s p(95)=1.96s
      { expected_response:true }............................................: avg=790.75ms min=41.6ms med=746.29ms max=2.54s p(90)=1.33s p(95)=1.49s
    http_req_failed.........................................................: 66.52% 4679 out of 7033
    http_reqs...............................................................: 7033   78.134156/s

    EXECUTION
    iteration_duration......................................................: avg=1.29s    min=41.8ms med=1.36s    max=3.23s p(90)=1.81s p(95)=1.96s
    iterations..............................................................: 7033   78.134156/s
    vus.....................................................................: 1      min=1            max=150
    vus_max.................................................................: 150    min=150          max=150

    NETWORK
    data_received...........................................................: 2.2 MB 25 kB/s
    data_sent...............................................................: 2.6 MB 29 kB/s




running (1m30.0s), 000/150 VUs, 7033 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s
ERRO[0090] thresholds on metrics 'http_req_duration, http_req_failed' have been crossed 


### ------------------------------------------------------------------------------------------


# 7.3 Frontend performance, accessibility & best practices

c)
- What metrics are contributing the most to the frontend perceived performance? What do
they mean?
HTTP Request Duration, HTTP Requests and HTTP Request Failures
- How would you make the site more accessible?
Proper HTML semantic, descriptive text on images, etc


### ------------------------------------------------------------------------------------------
