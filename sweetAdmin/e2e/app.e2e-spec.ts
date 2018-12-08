import { YjdmPage } from './app.po';

describe('yjdm App', () => {
  let page: YjdmPage;

  beforeEach(() => {
    page = new YjdmPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
