{:config {:order 0, :description "Incrementing and Decrementing", :name :inc-and-dec}
 :data
 [
  [:node-create [] :map]
  [:node-create [:main] :map]
  [:node-create [:main :my-counter] :map]
  [:value [:main :my-counter] nil 1]
  [:transform-enable [:main :my-counter] :inc [{:io.pedestal.app.messages/topic [:my-counter]}]]
  [:transform-enable [:main :my-counter] :dec [{:io.pedestal.app.messages/topic [:my-counter]}]]
  :break
  [:value [:main :my-counter] 1 2]
  :break
  [:value [:main :my-counter] 2 3]
  :break
  [:value [:main :my-counter] 3 4]
  :break
  [:value [:main :my-counter] 4 3]
  :break
  [:value [:main :my-counter] 3 2]
 ]}