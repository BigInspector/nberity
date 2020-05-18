import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElkoproductsComponent } from './elkoproducts.component';

describe('ElkoproductsComponent', () => {
  let component: ElkoproductsComponent;
  let fixture: ComponentFixture<ElkoproductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElkoproductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElkoproductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
