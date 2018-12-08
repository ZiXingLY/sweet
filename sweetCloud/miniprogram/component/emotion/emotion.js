// component/emotion/emotion.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    emotion: {
      type: Object,
      value: {
        authorImg: '../../images/user.gif',
        authorName: '安石',
        content: '<div>安石啊</div>'
      },
      observer: function (newVal, oldVal, changedPath) {
        // 属性被改变时执行的函数（可选），也可以写成在methods段中定义的方法名字符串, 如：'_propertyChange'
        // 通常 newVal 就是新设置的数据， oldVal 是旧数据
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    // emotion: {
    //   authorImg: '../../images/user.gif',
    //   authorName: '安石',
    //   content: '<div>安石啊</div>'
    // },
    nodes: [{
      name: 'div',
      attrs: {
        class: 'div_class',
        style: 'line-height: 60px; color: red;'
      },
      children: [{
        type: 'text',
        text: 'Hello&nbsp;World!'
      },
      {
        name: 'div',
        attrs: {
          class: 'div_class',
          style: 'line-height: 60px; color: red;'
        },
        children: [{
          type: 'text',
          text: 'inner-Hello&nbsp;World!'
        }]
      }]
    }]
  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})
