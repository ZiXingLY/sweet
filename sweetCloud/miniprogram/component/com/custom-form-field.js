Component({
  behaviors: ['wx://form-field'],

  attached() {
    
  },
  methods: {
    inputEvent(e){
      this.setData({
        value: e.detail.value
      })
    }
  }
})
