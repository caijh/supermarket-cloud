<script>
  import Pager from './Pager'

  export default {
    components: {Pager},
    name: 'Pagination',
    props: {
      pageSize: {
        type: Number,
        default: 10
      },
      current: {
        type: Number,
        default: 1
      },
      total: {
        type: Number
      },
      change: {
        type: Function,
        default () {
        }
      },
      pageSizeChange: {
        type: Function,
        default () {
        }
      },
      pagerStyle: {
        type: String
      },
      size: {
        type: String
      },
      simple: {
        type: Boolean,
        default: false
      },
      layout: {
        type: String,
        default: 'total, pager, sizer, jumper'
      },
      sizeOptions: {
        type: Array,
        default () {
          return [10, 20, 30, 40, 50]
        }
      },
      align: String
    },

    data () {
      return {
        interCurrent: 1,
        interPageSize: this.pageSize
      }
    },

    watch: {
      current (val) {
        if (val !== this.interCurrent) {
          this.handleChangePage(val)
        }
      },
      pageSize (val) {
        if (val !== this.interPageSize) {
          // this.handleChangePage(val);
        }
      }
    },

    computed: {
      totalPage () {
        return this.calcTotalPage()
      },
      pagerStyleClass () {
        return this.pagerStyle ? `is-${this.pagerStyle}` : ''
      },
      sizeClass () {
        return this.size ? `is-${this.size}` : ''
      },
      alignClass () {
        return this.align ? `is-${this.align}` : ''
      }
    },

    methods: {
      calcTotalPage () {
        return Math.floor((this.total - 1) / this.interPageSize) + 1
      },
      handleChangePage (p) {
        if (p !== this.interCurrent) {
          this.interCurrent = p
          this.change(p)
        }
      },
      handleJumpPrev () {
        this.handleChangePage(Math.max(1, this.interCurrent - 5))
      },
      handleJumpNext () {
        this.handleChangePage(Math.min(this.totalPage, this.interCurrent + 5))
      },
      hasPrev () {
        return this.interCurrent > 1
      },
      hasNext () {
        return this.interCurrent < this.totalPage
      },
      handlePrev () {
        this.handleChangePage(this.interCurrent - 1)
      },
      handleNext () {
        this.handleChangePage(this.interCurrent + 1)
      },
      handleQuickJumper (e) {
        let page = e.target.value
        page = Number(page)
        if (!page || isNaN(page)) return

        if (e.keyCode === 13) {
          this.handleChangePage(page)
        }
      },
      handlePageSizeChange (e) {
        const pageSize = e.target.value
        this.interPageSize = pageSize
        this.totalPage = this.calcTotalPage(pageSize)
        if (this.interCurrent > this.totalPage) {
          this.handleChangePage(this.totalPage)
        }
        this.pageSizeChange(this.interCurrent, pageSize)
      }
    },

    mounted () {
      this.handleChangePage(this.current)
    },

    render (h) {
      const alignClass = this.alignClass
      const sizeClass = this.sizeClass
      const styleClass = this.pagerStyleClass
      const pagerList = []
      let total = ''
      let jumper = ''
      let sizer = ''
      let pager = null
      let prevPager = null
      let nextPager = null
      let firstPager = null
      let lastPager = null

      const interCurrent = this.interCurrent

      if (!this.simple) {
        if (this.totalPage <= 6) {
          for (let i = 1; i <= this.totalPage; i++) {
            const active = interCurrent === i
            pagerList.push(
              <Pager pageNo={i} active={active} onClick={this.handleChangePage.bind(this, i)}/>
            )
          }
        } else {
          prevPager = (
            <li class="btn-jumper"><a class={`button is-primary is-inverted ${sizeClass}`}
                                      onClick={this.handleJumpPrev}><i class="fa fa-angle-double-left"></i></a></li>
          )
          nextPager = (
            <li class="btn-jumper"><a class={`button is-primary is-inverted ${sizeClass}`}
                                      onClick={this.handleJumpNext}><i class="fa fa-angle-double-right"></i></a></li>
          )
          firstPager = (
            <Pager active={false} pageNo={1}/>
          )
          lastPager = (
            <Pager active={false} pageNo={this.totalPage}/>
          )

          let left = Math.max(1, interCurrent - 2)
          let right = Math.min(interCurrent + 2, this.totalPage)

          if (interCurrent - 1 <= 2) {
            right = 1 + 4
          }

          if (this.totalPage - interCurrent <= 2) {
            left = this.totalPage - 4
          }

          for (let i = left; i <= right; i++) {
            const active = interCurrent === i
            pagerList.push(
              <Pager pageNo={i} active={active} onClick={this.handleChangePage.bind(this, i)}/>
            )
          }

          if (interCurrent - 1 >= 4) {
            pagerList.unshift(prevPager)
          }
          if (this.totalPage - interCurrent >= 4) {
            pagerList.push(nextPager)
          }

          if (left !== 1) {
            pagerList.unshift(firstPager)
          }
          if (right !== this.totalPage) {
            pagerList.push(lastPager)
          }
        }

        total = (<span>共 {this.total} 条</span>)
        jumper = (
          <span>跳转到 <input class={`input ${sizeClass}`} type="number" min="1" number="true"
                           on-keyup={this.handleQuickJumper}/></span>
        )
        sizer = (
          <span class={`select ${sizeClass}`}>
            <select onChange={this.handlePageSizeChange}>
              {this._l(this.sizeOptions, option => <option value={option}>{option} 条/页</option>)}
            </select>
          </span>
        )

        // const prevClass = this.hasPrev() ? 'pagination-previous' : 'pagination-previous is-disabled'
        // const nextClass = this.hasNext() ? 'pagination-next' : 'pagination-next is-disabled'
        pager = (
          <ul className="pagination-list">
            {this._l(pagerList, page => page)}
          </ul>
        )
      } else {
        // const prevClass = this.hasPrev() ? 'pagination-previous' : 'pagination-previous is-disabled'
        // const nextClass = this.hasNext() ? 'pagination-next' : 'pagination-next is-disabled'
        // pagerList.push(
        //   <a className={prevClass} onClick={this.handlePrev}><i className="fa fa-angle-left"></i></a>
        // )
        // pagerList.push(
        //   <a className={nextClass} onClick={this.handleNext}><i className="fa fa-angle-right"></i></a>
        // )
        pager = (
          <ul className="pagination-list">
            <li><input className={`input ${sizeClass}`} value={this.interCurrent} type="number" min="1" number="true"
                       on-keyup={this.handleQuickJumper}/></li>
            <li>/ {this.total}</li>
          </ul>
        )
      }

      const items = {
        total, pager, sizer, jumper
      }
      const components = this.layout.split(',')

      return (
        <nav class={`pagination ${styleClass} ${sizeClass} ${alignClass}`} role="navigation" aria-label="pagination">
          {components.map(item => items[item.trim()])}
        </nav>
      )
    }
  }
</script>

<style lang="scss">
  .pagination {
    font-size: 13px;
    &.is-small {
      font-size: 12px;
    }
    &.is-left {
      justify-content: flex-start;
    }
    &.is-right {
      justify-content: flex-end;
    }
    ul {
      flex-grow: 0;
      i {
        line-height: 24px;
      }
      .is-small i {
        line-height: 17px;
      }
    }
    .btn-jumper {
      width: 32px;
      cursor: pointer;
      a {
        display: none;
      }
      &:after {
        content: "\2022\2022\2022";
        display: block;
        letter-spacing: 2px;
        color: #ccc;
        text-align: center;
        font-size: 12px;
        font-family: Arial;
        margin: 0 3px;
      }
      &:hover {
        a {
          display: block;
        }
        &:after {
          display: none;
        }
      }
    }
    .input {
      display: inline-block;
      width: 45px;
      vertical-align: baseline;
    }
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
      /* display: none; <- Crashes Chrome on hover */
      -webkit-appearance: none;
      margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
    }
  }
</style>
