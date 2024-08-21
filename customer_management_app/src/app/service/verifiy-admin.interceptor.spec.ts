import { TestBed } from '@angular/core/testing';

import { VerifiyAdminInterceptor } from './verifiy-admin.interceptor';

describe('VerifiyAdminInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      VerifiyAdminInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: VerifiyAdminInterceptor = TestBed.inject(VerifiyAdminInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
