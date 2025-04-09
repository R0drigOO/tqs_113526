import http from 'k6/http';
import { check } from 'k6';

export const options = {
  stages: [
    { duration: '5s', target: 20 },
    { duration: '10s', target: 20 },
    { duration: '5s', target: 0 },
  ],
  thresholds: {
    // 95% das requisições devem ter duração < 1100ms
    'http_req_duration{p(95)}': ['< 1100'],
    // % de requisições com falha deve ser < 1%
    'http_req_failed': ['rate<0.01'],
    // Checks (asserts) devem ter sucesso > 98% das vezes
    'checks': ['rate>0.98'],
  },
};

export default function() {
  const res = http.get('http://localhost:8080/api/meals?restaurantId=1');

  check(res, {
    'status is 200': (r) => r.status === 200,
    'body size < 1k': (r) => r.body.length < 1024,
  });
}
